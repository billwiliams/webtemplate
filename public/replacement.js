function getScript(url, success) {
    var script = document.createElement('script');
    script.src = url;
    var head = document.getElementsByTagName('head')[0],
        done = false;
    // Attach handlers for all browsers
    script.onload = script.onreadystatechange = function() {
      if (!done && (!this.readyState
           || this.readyState == 'loaded'
           || this.readyState == 'complete')) {
        done = true;
        success();
        script.onload = script.onreadystatechange = null;
        head.removeChild(script);
      }
    };
    head.appendChild(script);
}

getScript ('http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js',function() {
    $(document).ready(function(){
    	$(".clearfix p:first-child, .page-header, form").hide();
		$(".brand").text("Neener");
		$(".clearfix p:nth-child(2)").css({"margin":"0 32%"});
		$("form").parent(".clearfix").append("<p style='white-space:nowrap; font-size:14px; color:grey; margin:120px 44%'>Copyright 2013</p>");
		$("body").show();
	});
});

