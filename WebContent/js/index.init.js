jQuery(function( $ ){
    /**
     * Demo binding and preparation, no need to read this part
     */
    //borrowed from jQuery easing plugin
    //http://gsgd.co.uk/sandbox/jquery.easing.php
    $.easing.elasout = function(x, t, b, c, d) {
        var s=1.70158;var p=0;var a=c;
        if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
        if (a < Math.abs(c)) { a=c; var s=p/4; }
        else var s = p/(2*Math.PI) * Math.asin (c/a);
        return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;
    };

    // Reset the screen to (0,0)
    $.scrollTo(0);
    
    // TOC, shows how to scroll the whole window
    $('#nav-steps a').click(function(){//$.scrollTo works EXACTLY the same way, but scrolls the whole screen
        $.scrollTo( this.hash, 1500, { easing:'elasout', axis:'y'});
        return false;
    });
    
    
});
