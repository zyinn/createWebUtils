/**
 * Created by lenovo on 2018/1/16.
 */
$(document).ready(function () {
    $.fn.stringifyArray = function(array) {
        return JSON.stringify(array)
    }

    $.fn.parseArray = function(array) {
        return JSON.parse(array)
    }

    $(".icon-add").on("click",function () {
        $(".login-user").append(
            '<div class="spot" style="margin-top: 10px;">' +
            '<input type="text" name="tableName"  placeholder="please enter table name" required class="add_input" style="width: 300px;height: 25px;  padding-top: 8px; border-radius:5px 0 5px 0; -moz-border-radius:15px; "/> ' +
            '<label class="remove" value="-" style="font-size: 40px; ">-</label></div>'
        ).find("label.remove").click(function(){
            $(this).parent().remove();
            // $('input#add').show();
        });
    })

    // $(".remove").mouseHooks(function (){
    //     $(".remove").css({"cursor":"pointer"});
    //     $(".remove").css("background-color","yellow");
    // })

    $("#bn").click(function () {
        var form=$(".form1").val();
        debugger;
        var dataJson=new Array();
        dataJson.push(form);

        var fields = $("input[name=tableName]").serializeArray();
        jQuery.each( fields, function(i, field){
            dataJson.push(field.value);
        });

        $.ajax({
            type: "POST",
            url: "autoGeneration",
            dataType : 'json',
            contentType : 'application/json',
            data: $.fn.stringifyArray(dataJson),
            success: function(msg){
                alert( "Data Saved: " + msg );
            }
        });
    })
})