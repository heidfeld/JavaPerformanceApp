$(document).ready(function(){
    $("#button-algorithm").click(function(){
        $.ajax({
            url: "/rest/algorithm/external",
            type: 'GET',
            success: function(result){
                $("#div1").html(result);
            }
        });
    });
});

$(document).ready(function(){
    $("#button-request-strike").click(function(){
        $.ajax({
            url: "/rest/request-strike/external",
            type: 'GET',
            success: function(result){
                $("#div2").html(result);
            }
        });
    });
});