function showPassword1() {
  var passwordInput1 = document.getElementById("passwordInput1");
  var showPassword1 = document.getElementById("showPassword1");

  if (passwordInput1.type === "password") {
    passwordInput1.type = "text";
    showPassword1.classList.remove("mdi-eye");
    showPassword1.classList.add("mdi-eye-off");
  } else {
    passwordInput1.type = "password";
    showPassword1.classList.remove("mdi-eye-off");
    showPassword1.classList.add("mdi-eye");
  }
}

function showPassword2() {
  var passwordInput2 = document.getElementById("passwordInput2");
  var showPassword2 = document.getElementById("showPassword2");

  if (passwordInput2.type === "password") {
    passwordInput2.type = "text";
    showPassword2.classList.remove("mdi-eye");
    showPassword2.classList.add("mdi-eye-off");
  } else {
    passwordInput2.type = "password";
    showPassword2.classList.remove("mdi-eye-off");
    showPassword2.classList.add("mdi-eye");
  }
}
