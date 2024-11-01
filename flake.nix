{
  description = "Controller support for Minecraft Java";

  inputs.flake-parts.inputs.nixpkgs-lib.follows = "nixpkgs";
  inputs.flake-parts.url = "github:hercules-ci/flake-parts";
  inputs.treefmt-nix.inputs.nixpkgs.follows = "nixpkgs";
  inputs.treefmt-nix.url = "github:numtide/treefmt-nix";
  inputs.systems.flake = false;
  inputs.systems.url = "github:nix-systems/default";
  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";

  outputs =
    inputs:
    inputs.flake-parts.lib.mkFlake { inherit inputs; } (
      { config, lib, ... }:
      let
        flakeConfig = config;
      in
      {
        imports = [
          inputs.treefmt-nix.flakeModule
        ];
        config.flake.overlays.controlablePackages = finalPkgs: prevPkgs: {
          controlablePackages = finalPkgs.makeScopeWithSplicing' {
            otherSplices = finalPkgs.generateSplicesForMkScope "controlablePackages";
            f =
              finalControlablePackages:
              let
                inherit (finalControlablePackages) callPackage;
              in
              {
                controlable-minimalDevShell = callPackage (
                  {
                    jdk21,
                    mkShell,
                    stdenv,
                    ...
                  }:
                  mkShell.override { inherit stdenv; } {
                    __structuredAttrs = true;

                    nativeBuildInputs = [
                      jdk21
                    ];
                  }
                ) { jdk21 = finalPkgs.__splicedPackages.jdk21_headless; };
                controlable-devShell = callPackage (
                  {
                    addDriverRunpath,
                    alsa-lib,
                    controlable-minimalDevShell,
                    eclipses,
                    flite,
                    glfw3-minecraft,
                    jdk21,
                    lib,
                    libGL,
                    libX11,
                    libXcursor,
                    libXext,
                    libXrandr,
                    libXxf86vm,
                    libjack2,
                    libpulseaudio,
                    libusb1,
                    mkShell,
                    openal,
                    pciutils,
                    pipewire,
                    stdenv,
                    udev,
                    vulkan-loader,
                    xrandr,
                    ...
                  }:
                  let
                    controlable-minimalDevShell' = controlable-minimalDevShell.override {
                      inherit jdk21 mkShell stdenv;
                    };
                    env' = controlable-minimalDevShell'.env;
                  in
                  mkShell.override { inherit stdenv; } {
                    __structuredAttrs = true;

                    inputsFrom = [
                      controlable-minimalDevShell'
                    ];
                    nativeBuildInputs = [
                      eclipses.eclipse-java
                    ];
                    buildInputs = [
                      pciutils
                      xrandr
                    ];
                    env = env' // {
                      LD_LIBRARY_PATH =
                        lib.makeLibraryPath [
                          addDriverRunpath.driverLink

                          ## native versions
                          glfw3-minecraft
                          openal

                          ## openal
                          alsa-lib
                          libjack2
                          libpulseaudio
                          pipewire

                          ## glfw
                          libGL
                          libX11
                          libXcursor
                          libXext
                          libXrandr
                          libXxf86vm

                          udev # oshi

                          vulkan-loader # VulkanMod's lwjgl

                          flite

                          libusb1
                        ]
                        + (if env'.LD_LIBRARY_PATH or "" == "" then "" else ":${env'.LD_LIBRARY_PATH}");
                    };
                  }
                ) { };
              };
          };
        };
        config.perSystem =
          {
            config,
            pkgs,
            system,
            ...
          }:
          {
            config._module.args.pkgs = import inputs.nixpkgs {
              inherit system;
              config.allowUnfree = false;
              overlays = [
                flakeConfig.flake.overlays.controlablePackages
              ];
            };
            config.devShells.controlable-minimal =
              pkgs.__splicedPackages.controlablePackages.controlable-minimalDevShell;
            config.devShells.controlable = pkgs.__splicedPackages.controlablePackages.controlable-devShell;
            config.devShells.default = pkgs.__splicedPackages.controlablePackages.callPackage (
              {
                controlable-devShell,
                mkShell,
                stdenv,
                ...
              }:
              let
                controlable-devShell' = controlable-devShell.override { inherit mkShell stdenv; };
              in
              mkShell.override { inherit stdenv; } {
                __structuredAttrs = true;

                inputsFrom = [
                  controlable-devShell'
                  config.treefmt.build.devShell
                ];

                env = controlable-devShell'.env;
              }
            ) { };
            config.legacyPackages.nixpkgs = lib.dontRecurseIntoAttrs pkgs;
            config.treefmt = {
              config.programs.nixfmt.enable = true;
              config.projectRootFile = ".github/README.md";
            };
          };
        config.systems = import inputs.systems;
      }
    );
}
