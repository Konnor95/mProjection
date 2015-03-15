<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<!--[if IE 9]>
<html class="ie9" lang="en">
<![endif]-->

<head>
    <title>mProjection</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/style.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/animate.css"/>" />
    <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700%7COpen+Sans:300,400' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/font-awesome/css/font-awesome.min.css"/>" />
    <link rel="icon" href="<c:url value="/assets/images/faviconlogo.png"/>" />
</head>

<body>

<!-- BEGIN MAIN -->
<div class="section-main" id="top">
    <div id="right-c" class="mini-container main-mini-container">

        <div class="title">
            <div class="header">
                <h1>mProjection</h1>
                <h2>coming soon</h2>
            </div>
            <div class="content">
                <div id="countdown">
                    <div class="countdown-elem elem-days">

                        <h2 class="days">00</h2>

                        <span class="time_Days">days</span>
                    </div>
                    <!--
                    -->
                    <div class="countdown-elem elem-hours">
                        <h2 class="hours">00</h2>

                        <span class="time_Hours">hours</span>
                    </div>
                    <!--
                    -->
                    <div class="countdown-elem elem-minutes">
                        <h2 class="minutes">00</h2>
                        <span class="time_Minutes">minutes</span>
                    </div>
                    <!--
                    -->
                    <div class="countdown-elem elem-seconds">
                        <h2 class="seconds">00</h2>
                        <span class="time_Seconds">seconds</span>
                    </div>
                </div>
                <div class="navigation">
                    <a class="nav-button" target="_blank" href="https://youtu.be/dJwufJquqUs">view teaser</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END MAIN -->
<!-- Loading Page Overlay -->
<div class="page-loading">
    <noscript><div class="noscript-error"><i class="fa fa-info-circle"></i>Enable JavaScript to see this site working properly.</div></noscript>
    <div class="square-loader-wrap">
        <div class="square-loader"></div>
        <div class="square-loader"></div>
        <div class="square-loader"></div>
        <div class="square-loader"></div>
    </div>
</div>
<!-- JAVASCRIPT -->
<script type="text/javascript" src="<c:url value="/assets/js/jquery.js"/>"></script>

<!-- All set up / customisation variables for slideshow and video background etc. in custom.js file -->
<script type="text/javascript" src="<c:url value="/assets/js/custom.js"/>"></script>

<script type="text/javascript" src="<c:url value="/assets/js/finely.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/countdown.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/emailsubscribe.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/contact.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/jquery.backstretch.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/jquery.mb.YTPlayer.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/placeholders.min.js"/>"></script>
<script type="text/javascript">

    // No need to edit here - all settings in custom.js file

    $(document).ready(function () {

        if (videoBackground == 'on') {
            if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
                $(".player").hide();
                $.backstretch(slideshowBackgroundURLS, {
                    fade: slideshowFade,
                    duration: slideshowDuration
                });
                $('.backstretch img').load(function () {
                    if (preloader == 'on') {
                        $('.page-loading').delay(1500).fadeOut(1000, function () { animateInStart(); });
                    }
                    else {
                        animateInStart();
                    }
                });
            }
            else {
                $(".player").mb_YTPlayer({
                    videoURL: videoBackgroundURL,
                    containment: 'body',
                    autoPlay: true,
                    mute: videoMuted,
                    startAt: 0,
                    opacity: 1
                });
                $('#bgndVideo').on("YTPStart", function () {
                    if (preloader == 'on') {
                        $('.page-loading').delay(1500).fadeOut(1000, function () { animateInStart(); });
                    }
                    else {
                        animateInStart();
                    }
                });

                if (videoMuted == false) {
                    $('.videoMuteButton').show();
                }
            }
        }
        else {
            $.backstretch(slideshowBackgroundURLS, {
                fade: slideshowFade,
                duration: slideshowDuration
            });
            $('.backstretch img').load(function () {
                if (preloader == 'on') {
                    $('.page-loading').delay(1500).fadeOut(1000, function () { animateInStart(); });
                }
                else {
                    animateInStart();
                }
            });
        }

        $("#countdown").countdown({ date: countdownDate });

        if ($(window).width() < 599) {
            $('.about-container .about-box-spacer').css('height', '1px');
        }
        else {
            var ieAboutSpacerHeight = $('.about-box-text').height() + 'px';
            $('.about-container .about-box-spacer').css('height', ieAboutSpacerHeight);
        }
    });
</script>
</body>
</html>