function showPassword1() {
  var passwordInput = document.getElementById("passwordInput");
  var showPassword1 = document.getElementById("showPassword1");

  if (passwordInput.type === "password") {
    passwordInput.type = "text";
    showPassword1.classList.remove("mdi-eye");
    showPassword1.classList.add("mdi-eye-off");
  } else {
    passwordInput.type = "password";
    showPassword1.classList.remove("mdi-eye-off");
    showPassword1.classList.add("mdi-eye");
  }
}

function showPassword2() {
  var passwordInput = document.getElementById("passwordInput");
  var showPassword2 = document.getElementById("showPassword2");

  if (passwordInput.type === "password") {
    passwordInput.type = "text";
    showPassword2.classList.remove("mdi-eye");
    showPassword2.classList.add("mdi-eye-off");
  } else {
    passwordInput.type = "password";
    showPassword2.classList.remove("mdi-eye-off");
    showPassword2.classList.add("mdi-eye");
  }
}
