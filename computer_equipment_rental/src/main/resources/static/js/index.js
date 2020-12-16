load = async () => {
    let jwt = localStorage.getItem("jwt");
    if (jwt == null) {
        document.location.href = "/login";
    }
}

onAdminPage = () => {
    document.location.href = "/register";
}

logout = () => {
    localStorage.clear();
    document.location.href = "/login";
}

load();