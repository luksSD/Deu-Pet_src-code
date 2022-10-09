$(document).ready(function() {

    alertEmptyTable();

    $("input:checkbox").on("click", function() {

        $("#table-body tr").hide()

        var flag = 1;
        var cbAnimalArray = [];
        var cbStatusArray = [];
        var dogFlag = false;
        var catFlag = false;
        var adoptedFlag = false;
        var adoptFlag = false;

        $("input:checkbox[name=animal]:checked").each(function(){
            flag = 0;
            var value = $(this).val().toLowerCase();

            if ((value.toLowerCase().indexOf("cachorro") > -1)){
                dogFlag = true;
            } else{
                catFlag = true;
            }
        });

        $("input:checkbox[name=status]:checked").each(function(){
            flag = 0;
            var value = $(this).val().toLowerCase();

            if (value.toLowerCase().indexOf("adotado") > -1){
                adoptedFlag = true;
            } else{
                adoptFlag = true;
            }
        });

        if (flag == 1){
            $("#table-body tr").show()
        } else{
            $("#table-body tr").filter(function() {

                if((dogFlag == true && catFlag == true) || (adoptedFlag == true && adoptFlag == true)){
                    $(this).show()
                } else if((dogFlag == true) && ($(this).text().toLowerCase().indexOf("cachorro") > -1)){
                    if(adoptFlag == true && adoptedFlag == false){
                       if ($(this).text().toLowerCase().indexOf("para adoção") > -1)
                          $(this).show()
                    } else if(adoptFlag == false && adoptedFlag == true){
                        if ($(this).text().toLowerCase().indexOf("adotado") > -1)
                           $(this).show()
                    } else {
                        $(this).show()
                    }
                } else if((catFlag == true) && ($(this).text().toLowerCase().indexOf("gato") > -1)){
                    if(adoptFlag == true && adoptedFlag == false){
                       if ($(this).text().toLowerCase().indexOf("para adoção") > -1)
                          $(this).show()
                    } else if(adoptFlag == false && adoptedFlag == true){
                        if ($(this).text().toLowerCase().indexOf("adotado") > -1)
                           $(this).show()
                    } else {
                        $(this).show()
                    }
                } else if((adoptedFlag == true) && ($(this).text().toLowerCase().indexOf("adotado") > -1)){
                    if(dogFlag == true && catFlag == false){
                       if ($(this).text().toLowerCase().indexOf("cachorro") > -1)
                          $(this).show()
                    } else if(dogFlag == false && catFlag == true){
                        if ($(this).text().toLowerCase().indexOf("gato") > -1)
                           $(this).show()
                    } else {
                        $(this).show()
                    }
                } else if((adoptFlag == true) && ($(this).text().toLowerCase().indexOf("para adoção") > -1)){
                    if(dogFlag == true && catFlag == false){
                       if ($(this).text().toLowerCase().indexOf("cachorro") > -1)
                          $(this).show()
                    } else if(dogFlag == false && catFlag == true){
                        if ($(this).text().toLowerCase().indexOf("gato") > -1)
                           $(this).show()
                    } else {
                        $(this).show()
                    }
                }
            });
        }
        alertEmptyTable();
    });

    function alertEmptyTable(){
      if($('#custom-table tbody tr:visible').length <= 0){
          $("#noEntryAlert").show()
      } else{
          $("#noEntryAlert").hide()
      }
    }
});

