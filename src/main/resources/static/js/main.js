(function ($) {
    "use strict";

    var removeSpinner = function () {
        setTimeout(function () {
            if ($('#spinner').length) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    removeSpinner();

    new WOW().init();

    $(window).on('scroll', function () {
        var nav = $('.navbar');
        if ($(this).scrollTop() > 45) {
            nav.addClass('sticky-top shadow-sm');
        } else {
            nav.removeClass('sticky-top shadow-sm');
        }
    });

    const $dropdown = $(".dropdown"),
          $toggle = $(".dropdown-toggle"),
          $menu = $(".dropdown-menu"),
          activeClass = "show";

    $(window).on("load resize", function () {
        if (this.matchMedia("(min-width: 992px)").matches) {
            $dropdown.hover(
                function () {
                    const $el = $(this);
                    $el.addClass(activeClass)
                        .find($toggle).attr("aria-expanded", "true");
                    $el.find($menu).addClass(activeClass);
                },
                function () {
                    const $el = $(this);
                    $el.removeClass(activeClass)
                        .find($toggle).attr("aria-expanded", "false");
                    $el.find($menu).removeClass(activeClass);
                }
            );
        } else {
            $dropdown.off("mouseenter mouseleave");
        }
    });

    $('[data-toggle="counter-up"]').counterUp({
        delay: 10,
        time: 2000
    });

    $(window).on('scroll', function () {
        const btn = $('.back-to-top');
        if ($(this).scrollTop() > 100) {
            btn.fadeIn('slow');
        } else {
            btn.fadeOut('slow');
        }
    });

    $('.back-to-top').on('click', function () {
        $('html, body').animate({ scrollTop: 0 }, 1500, 'easeInOutExpo');
        return false;
    });

    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        dots: true,
        loop: true,
        center: true,
        responsive: {
            0: { items: 1 },
            576: { items: 1 },
            768: { items: 2 },
            992: { items: 3 }
        }
    });

    $('.vendor-carousel').owlCarousel({
        loop: true,
        margin: 45,
        dots: false,
        autoplay: true,
        smartSpeed: 1000,
        responsive: {
            0: { items: 2 },
            576: { items: 4 },
            768: { items: 6 },
            992: { items: 8 }
        }
    });

})(jQuery);
