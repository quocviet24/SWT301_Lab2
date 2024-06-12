function profileSubmit(formId){
    preventSubmit(formId);
    let form = document.getElementById(formId);
    let phone = document.getElementById("phone-input").value;

    if (phone !== ""){
        if (isValidPhoneNumber) {
            alert("Phone number is not valid!");
            return;
        }
    }

    form.submit();
}
function changePasswordSubmit(formId){
    preventSubmit(formId);

    let password = document.getElementById("newPass-input").value;
    let repassword = document.getElementById("confirmPass-input").value;
    let form = document.getElementById(formId);

    if (password !== repassword) {
        alert("Re-password not match!");
        return;
    }

    form.submit();
}

function registerSubmit(formId) {
    preventSubmit(formId);

    let email = document.getElementById("email-input").value;
    let password = document.getElementById("password-input").value;
    let repassword = document.getElementById("repassword-input").value;
    let phone = document.getElementById("phone-input").value;
    let form = document.getElementById(formId);

    if (!isValidEmail(email)) {
        alert("Email " + email + " is not valid!");
        return;
    }


    if (phone !== ""){
        if (phone.length != 10) {
            alert("Phone number is not valid!");
            return;
        }
        if (!isValidPhoneNumber) {
            alert("Phone number is not valid!");
            return;
        }
    }

    if (password.length < 8) {
        alert("Password must be at least characters!");
        return;
    }

    if (password !== repassword) {
        alert("Re-password not match!");
        return;
    }
    form.submit();
}

function isValidEmail(email) {
    let emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(email);
}

function isValidPhoneNumber(phoneNumber) {
    // Regular expression to match exactly 10 digits
    let phoneRegex = /^[0-9]+$/;

    // Test the phone number against the regex
    return phoneRegex.test(phoneNumber);
}

function preventSubmit(id){
    document.getElementById(id).addEventListener('submit', function(event) {
        // Prevent form submission
        event.preventDefault();
    });
}
