# JarHopper
Hop Jars from a folder to the plugins folder! Simply stuff the plugins/JarHopper folder full of plugins / plugins that already exist, but need updated, and use /jh! JarHopper will automatically handle new or existing plugins by loading or reloading!
### Note
ENSURE THAT THE JAR FILE IS NAMED AS THE PLUGIN IS! For example, to Hop the plugin "Essentials", the file needs to be named "Essentials.jar". This is in case Essentials already exists. JarHopper cant figure out what the plugin is, without loading it first.
### Error Acronyms
* UDE: Unknown Dependency Eception. Bukkit has no idea what apis that plugin requires.
* IPE: Invalid Plugin Exception. Bukkit does not understand the jar file, or the plugin is failing to load (errors)
* IDE: Invalid Plugin Description. Bukkit does not understand the plugin.yml. It's invalid.
* UPE: Unknown Plugin Exception. The plugin failed to load for an unknown reason (check the console)
* INE: Invalid Name Exeption: JarHopper cannot load the plugin because it is already loaded, but cannot find the plugin because the jar file was not named exactly as the plugin's name is. (Read the NOTE above)

## Downloads
[RELEASE v1.0](https://github.com/danielmills/JarHopper/blob/master/build/latest/release/JarHopper.jar?raw=true)
