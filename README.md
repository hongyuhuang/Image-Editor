## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).



## Zoom (Steve Mills)
- Accessed via: View menu (Zoom in, Zoom out, Zoom full); Keyboard shortcut Ctrl-=, Ctrl--, Ctrl -1.
- Tested via Steve Mills.
- Error when trying to zoom full a jpg on mac. Zoom full doesn't work until another action of any type has been done.

## Greyscale Conversion (Steve Mills)
- Accessed via: Colour menu (Greyscale option); Keyboard shortcut Ctrl-G.
- Tested on both colour and already greyscale images. No formal testing framework was used, but test cases included both portrait (tall, narrow) and landscape (wide, shallow) images to make sure the loops over the pixels were correct. Test cases with both very dark and bright pixels were used to check for overflow between colour channels.
- No known issues.

## Sharpen Filter (Hongyu Huang)

- Accessed via: Filter menu (Sharpen Filter option); Keyboard shortcut Ctrl-V.
- Tested on both colour and greyscale images to see if it would be rendered with the differences enhanced between the neighbours. No formal testing framework was used. Test cases included both portrait and landscape images to test that the Gaussian blur equation loops correctly. I tested this [picture](https://upload.wikimedia.org/wikipedia/commons/4/43/Unsharped_eye.jpg) to see if with the sharpen filter implemented it would become more like the "eye" at the bottom.
- Edge cases are currently not being considered leading to a black border.

## Gaussian blur filter (Hongyu Huang)

- Accessed via: Filter menu (Gaussian Filter option); Keyboard shortcut Ctrl-C.
- Implemented via a slider with bounds from 0 to 10 in steps of 1 with 0 indicating a radius of 1 and 10 indicating a radius of 10.
- Tested on both colour and greyscale halftone images to see if it would be rendered smooth (e.g. [Halftone image being smoothed](https://upload.wikimedia.org/wikipedia/commons/d/d7/Halftone%2C_Gaussian_Blur.jpg)). No formal testing framework was used. Test cases included both portrait and landscape images to test that the Gaussian blur equation loops correctly. I tested against the Mean filter to ensure that the smoothing of the Gaussian blur filter can be seen. 
- Edge cases are currently not being considered leading to a black border.

## Median filter (Michael Young)

- Accessed via: Filter menu (Median Filter option); Keyboard shortcut Ctrl-B.
- Tested on both square and rectangle images as well as a portrait and landscape image. Used median filter test images to confirm the filter was working correctly. No formal testing framework was used.
- No known issues.

## Brightness adjustment (Paddy Borthwick)

- Accessed via: Colour menu (Adjust Brightness option); Keyboard shortcut Ctrl-L.
- Implemented via a slider with bounds from -100 to 100 in steps of 10 with -100 to 0 being a darken and 0 to 100 being a brighten.
- Tested on both very bright images and very dark images. This all acted as expected at the extreme values with darkening setting all pixels to the same grey value and brightening setting everything to either black or white.
- No known errors.

## Contrast adjustment (Paddy Borthwick)

- Accessed via: Colour menu (Adjust Contrast option); Keyboard shortcut Ctrl-K.
- Implemented via a slider with bounds from -100 to 100 in steps of 10 with -100 to 0 being a decrease in contrast and
0 to 100 being an increase in contrast.
- Tested on very matt images and very vivid images with decreses in contrast always ending with grey images and increases ending with very brigh images.
- No known errors.


## Image resize (Paddy Borthwick)

- Accessed via: Image menu (Resize Image option); Keyboard shortcut Ctrl-R.
- Can input values from 0.01 to 10.00 with 0.01 to 1 being a decrease and 1 to 10 being an increase.
- Tested within the full range of values for differing sized images. Also tried entering out of bounds values with no exceptions.
- No known errors.

## Image rotations (Michael Young)

- Accessed via: Image menu (Rotate Image Clockwise option); Keyboard shortcut Ctrl-4; Image menu (Rotate Image AntiClockwise option); Keyboard shortcut Ctrl-6.
- Tested on both square and rectangle images as well as a portrait and landscape images. No formal testing framework was used.
- No known issues.

## Image flip (Paddy Borthwick)

- Accessed via: Image menu (Flip Image Horizontal option); Keyboard shortcut Ctrl-5; Image menu (Flip Image Vertical option); Keyboard shortcut Ctrl-8.
- Tested on both when an image was open and when one wasn't.  No formal testing framework was used, however, it was tested on both horizontal and vertical images with no errors.
- No known issues.

## Image export (Andrew Clarkson)

- Accessed via: File menu (Export option); Keyboard shortcut Ctrl-E.
- Tested on multiple different images. No formal testing framework was used, however it was tested on images with multiple different filters used.
- When exporting an image you have to specify the format or it doesn't save properly.

## Exception handling (Andrew Clarkson)

- To handle exceptions, all of the action methods within Colour, Edit, File, Filter, Image and View action classes use a try-catch block to catch any exceptions and prints an Invalid Action error message to the terminal while stopping the program from crashing which was happening before these exception handling techniques were put into place.
- When opening a file there is an exception thrown that I couldn't figure out before release, however as far as I can tell this doesn't affect the functionality of the program.

## Other error avoidance/prevention (All team members)

- When we tried to input values that couldn't work for things like filters.
- File Save As error: Code was calling showOpenDialog rather than showSaveDialog, which didn't work on MacOS.
- To avoid people entering values that were out of bounds and potentially throwing errors, sliders were added to ensure that
only values on the expected bounds could be added.


## Toolbar for common operations (Andrew Clarkson)

- The actions that were added to the toolbar were selected by Andrew when he created it. The toolbar has actions from File, Edit, Colour, Image and View menus and are all used most commonly.
- No known issues.

## Icon Images for Toolbar (Andrew Clarkson)

- The png images Bright, Export, GreyScale, M-, M+, Redo, Save, Save1, Sharpen and Undo were all created from scratch in the free GIMP software.

## Keyboard shortcuts (Andrew Clarkson)

- The keyboard shortcuts for each feature were selected by Andrew when implementing the feature.
- No formal method has been used but each shortcut has been tested both when an image is loaded and when there is no image loaded. When there is no image loaded the exception handling processes of each method take effect.
- No known issues.

## Extended filters (Paddy Borthwick, Michael Young, Hongyu Huang)
- Accessed via the Filter menu (Mean, Sharpen, Gaussian and Median)
- Filter images taking into the edge cases.
- Tested on the same images used in the first half to ensure that the edge cases didn't affect how the filters looked. Ensured that the edges would no longer have black borders around it. Tested on various image types and sizes.
- No known issues.

## Filters with negative results (Emboss and sobel) Paddy Borthwick
- Accessed via the Filter menu (Emboss option and sobel)
- Select a direction you would like the filter to act in via the drop down menu
- Tested on a variety of image types and sizes
- No known issues


## Edge detection filters (Paddy Borthwick, Michael Young, Hongyu Huang)
- Accessed via the Filter menu (Mean, Sharpen, Gaussian and Median)
- Filter images taking into the edge cases.
- Tested on the same images used in the first half to ensure that the edge cases didn't affect how the filters looked. Ensured that the edges would no longer have black borders around it. Tested on various image types and sizes.
- No known issues.

## Posterise effect (Paddy Borthwick)
- Accessed via the colour menu (Posterise)
- Select the colour channels you want to use via the check boxes
- Tested on both coloured and grey scale images
- No known issues

## Mouse Selection (Andrew Clarkson)
- Works whenever ANDIE is running.
- Pressing mouse sets first point and dragging and releasing creates a rectangle using the start and end points and parameters.
- Selection is removed when mouse is clicked instead of pressed and dragged.

## Crop to selection (Hongyu Huang, Andrew Clarkson)
- Assesed via the Image menu.
- Is passed the mouse selection area from ImagePanel. 
- No known errors.
  
## Drawing functions (Andrew Clarkson)
- Accessed via the Draw menu.
- Select a region with the mouse first and then select the draw tool you want to use.
- The line will be drawn from the starting point to the end point of the mouse selection area.
- Doesn't work correctly on scaled(Zoom In & Out) images.
- Doesn't work when no image is loaded
  
## Macros (Michael Young)

## Text Box (Michael Young, Hongyu Huang, Andrew Clarkson)
- Aceesed via the Text menu.
- Select a region with mouse first and then select using the GUI what text you want aswell as the font family, font size, bold, italics and colour of the text.
- Tested various combinations of locations, text, font families, font sizes, boldm italics and colours.
- Known issue is that on Macs after pressing okay the colour chooser doesn't cover the origninal dialog.
- When pressing undo and redo the 

