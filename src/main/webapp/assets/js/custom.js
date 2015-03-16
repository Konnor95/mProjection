// ************************************************************* //
// SETTINGS for FINELY Responsive Under Construction Template
// Version 1.0
// By CosmicLabs
// Template available for purchase from Themeforest.net
// http://themeforest.net/user/cosmiclabs/portfolio
// ************************************************************* //


// To setup the template, change the variables below
// Follow instructions in code comments or see documentation for more detail


// ************************************************************* //
// ==== GENERAL SETTINGS ====
// Enable or disable the preloader by changing var preloader to 'on' or 'off' (disabling it is not reccomended)
// Enable or disable the background image overlay (the squares) using 'on' or 'off'
// Edit the colour of the CSS background overlay which darkens background images/video by using '#HEX' - use a HEX colour
// Edit the opacity of the colour overlay - determine how visible the background images/video are between '0' and '1.0'

var preloader = 'on';

var bgImgOverlay = 'on';
var bgOverlayColour = '#000000';
var bgOverlayOpacity = '0.4';


// ************************************************************* //
// ==== SLIDESHOW BACKGROUND ====
// Set URLs to background images inside the array
// Each image must be on its own line, inbetween speech marks (" ") and with a comma at the end of the line
// Add / remove images by changing the number of lines below
// To use a single (static) image background, only use one image line below
// Variable slideshowFade = transition speed for fade animation, in milliseconds
// Variable slideshowDuration = time each slide is shown for, in milliseconds

var slideshowBackgroundURLS = [
	"../../assets/images/soldier.png"
];

var slideshowFade = 1000;
var slideshowDuration = 4000;


// ************************************************************* //
// ==== VIDEO BACKGROUND ====
// Enable Video Background - 'on' to enable, 'off' to disable
// Cannot work on mobile devices due to device restrictions, instead shows slideshow
// If disabled slideshow is shown
// Paste YouTube URL into the videoBackgronudURL variable - use YouTube link NOT embed code
// Set videoMuted to 'true' and background videos will be muted with no toggle mute button visible
// Set videoMuted to 'false' and videos will not be muted, and the toggle mute button will be visible on the site so users can turn off audio if they want

var videoBackground = 'off';
var videoBackgroundURL = 'https://www.youtube.com/watch?v=sBWPCvdv8Bk';
var videoMuted = true;


// ************************************************************* //
// ==== COUNTDOWN ====
// Set up the date and time that will be counted down to
// Enter date in the format (24 hr clock): DD Month YYYY HH:MM:SS for example: 01 January 2015 23:04:00

var countdownDate = "9 September 2015 00:00:00";