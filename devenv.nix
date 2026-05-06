# java/app/devenv.nix
{pkgs, ...}: let
  application_name = "application_name";
in {
  # https://devenv.sh/basics/
  env.GREET = "Java & Spring Boot Development";
  env.application_name = application_name;

  dotenv.enable = true;

  packages = with pkgs; [
    gradle
  ];

  languages.java = {
    enable = true;
    jdk.package = pkgs.temurin-bin-21;
  };

  enterShell = ''
    echo "--- $GREET ---"
    java -version
    gradle -version
  '';
}
