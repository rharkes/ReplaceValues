# ReplaceValues
ImageJ plugin that does just that.

This macro will replace the zeros in an 8-bit image with NaN by converting to 32-bit and running the plugin.
```
//make the background of an 8-bit image NaN
run("Close All");
run("Particles (75K)");
rename("test");
selectWindow("test");
run("32-bit");
newImage("replace", "32-bit black", 1, 1, 1); //zero
newImage("with", "32-bit black", 1, 1, 1); //zero
run("Divide...", "value=0.000"); //make second zero NaN
run("Replace Value", "img=test replace=replace replacewith=with");
```