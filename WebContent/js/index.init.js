jQuery(function( $ ){

    // Reset the screen to (0,0)
    $.scrollTo(0);
    
    // TOC, shows how to scroll the whole window
    $('#nav-steps a').click(function(){//$.scrollTo works EXACTLY the same way, but scrolls the whole screen
        $.scrollTo( this.hash, 1000, {axis:'y'});
        return false;
    });
    
    
});
