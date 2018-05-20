$(document).ready(function(){
    $("#button-algorithm").click(function(){
        $("#loader1").css("display", "block");
        $.ajax({
            url: "rest/algorithm/dijkstra2",
            data: $("#algorithmForm").serialize(),
            type: 'POST',
            dataType: 'json',
            success: function(result){
                var textResult = JSON.stringify(JSON.parse(result),null,2);
                $("#div1").html(textResult);
                $("#loader1").css("display", "none");
            }
        });
    });
});

$(document).ready(function(){
    $("#button-request-strike").click(function(){
        $("#loader2").css("display", "block");
        $.ajax({
            url: "/rest/request-strike/external",
            type: 'GET',
            success: function(result){
                $("#div2").html(result);
                $("#loader2").css("display", "none");
            }
        });
    });
});