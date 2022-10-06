
function validatePassword() {
        const password = document.getElementById('new-password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        if (confirmPassword != password) {
            document.getElementById("errorMessageDiv").classList.remove('d-none');
            document.getElementById("errorMessageDiv").innerHTML="As senhas são diferentes!";
            event.preventDefault();
        } else if (!confirmPassword || !password){
            document.getElementById("errorMessageDiv").classList.remove('d-none');
            document.getElementById("errorMessageDiv").innerHTML="Senha não preenchida!";
            event.preventDefault();
        } else{
            clearErrorMessage();
        }
}

function clearErrorMessage() {
  var element = document.getElementById("errorMessageDiv"); /* finding and assigning element to variable element */
  document
  document.getElementById("errorMessageDiv").classList.add('d-none');// then deleting the parent and child (please refer to link)
}

