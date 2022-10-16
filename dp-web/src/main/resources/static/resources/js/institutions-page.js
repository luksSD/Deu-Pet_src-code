$(document).ready(function() {

    alertEmptyTable();

});

function alertEmptyTable(){
if($('#custom-table tbody tr:visible').length <= 0){
  $("#noEntryAlert").show()
} else{
  $("#noEntryAlert").hide()
}
}
