OWLdroid
========

Embed OWL (Outliner with Wiki Linking) in an Android app.

What is this?
-------------

This takes [OWL](https://github.com/interstar/OWL), the Concord-derived Outliner with Wiki Linking, and embeds it in an Android app. using WebView.

The outliner / wiki works just as the in-browser version, but the Android app. now provides the layer to save pages as OPML files to the SD Card of your Android device.



Status
------

*tl;dr* : It's very, very crude and experimental. It just about works for me, but I've not tested it widely. If you're interested in this approach and want some help getting it working, then get in touch with me. 

I've now refactored so that the code-base of OWLdroid, while still a fork of OWL / Concord, is largely in the same directory structure as the original OWL.

The "Quick" menu is now working for moving and deleting nodes. But remember nodes can only be deleted in non-text mode, which is still 
hard to enter in the OWLdroid version.


Gotchas
--------

I'm saving the pages into a directory on your Android device called /sdcard/OWL/ ... make sure you've created it. It won't work without this.

The little arrow icons (due to font-awesome) that Concord uses to distinguish between open / closed suboutlines, don't actually appear in 
the OWLdroid version (some font-awesome problem I haven't fixed yet). Opening / closing on the left of the line *does* work. But it's hard to select an invisible icon!



