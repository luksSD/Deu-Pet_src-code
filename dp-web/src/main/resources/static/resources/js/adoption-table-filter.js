$(document).ready(function() {
    $("input:checkbox").on("click", function() {

        $("#animal-table tr").hide()
        var flag = 1
        $("input:checkbox:checked").each(function() {
            flag = 0;
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function() {
                if ($(this).text().toLowerCase().indexOf(value) > -1)
                    $(this).show()
            });
        });
        if (flag == 1)
            $("#myTable tr").show()
    });
});