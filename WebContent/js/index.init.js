jQuery(function( $ ){

    // Reset the screen to (0,0)
    $.scrollTo(0);
    
    // TOC, shows how to scroll the whole window
    $('#nav-steps div').click(function(){//$.scrollTo works EXACTLY the same way, but scrolls the whole screen
        $.scrollTo( this.title, 1000, {easing: 'easeOutQuad',axis:'y'});
        return false;
    });
    
    
});
