function clearFields(){
    document.getElementById("smusername").innerHTML = "";
    document.getElementById("smemail").innerHTML = "";
    document.getElementById("smpassword").innerHTML = "";
    document.getElementById("smfirstName").innerHTML = "";
    document.getElementById("smlastName").innerHTML = "";
    document.getElementById("smsubmitPassword").innerHTML = "";
    document.getElementById("res").innerHTML = "";
}

function clearFormFields(){
    document.getElementById("email").value.innerHTML = "";
    document.getElementById("password").value.innerHTML = "";
    document.getElementById("firstName").value.innerHTML = "";
    document.getElementById("username").value.innerHTML = "";
    document.getElementById("lastName").value.innerHTML = "";
    document.getElementById("password").value.innerHTML = "";
}

async function Register()
{
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    clearFields();
    if(password !== document.querySelector("#submitPassword").value)
    {
        document.getElementById("smsubmitPassword").innerHTML = "Passwords are not the same";
        return
    }
    let response = await fetch("/api/authentication/register",
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            body: JSON.stringify({
                username: username,
                email: email,
                password: password,
                firstName: firstName,
                lastName: lastName,
            })
        });
    if(response.status === 200)
    {
        document.getElementById("res").innerHTML = "Ypu are registered";
        clearFormFields();
        return;
    }
    else
    {
        let data = await response.json();
        data.errors.forEach(err =>
        {
            document.getElementById("sm" + err.field).innerHTML = err.message;
        });
    }
}

hide_result = () =>{
    document.getElementById("res").innerHTML = "";
}