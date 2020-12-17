async function setUserRentFormById(data,token) {
    return await fetch("/admin/devices",{
        method :'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function getAllUserRentByUserId(id, token) {
    return await fetch(`api/user/${id}`,{
        method :'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}
async function getAllUserRentByRent(data, token) {
    return await fetch(`/api/devices/${data}`,{
        method :'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}

async function isUserRentExistByComputerStuffId(data, token) {
    return await fetch("/admin/getUserDevices",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function isUserRentExistByUserId(data, token) {
    return await fetch("/user/isUserRentExistByUserId",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    })
}
async function createUserRent(data, token) {
    return await fetch("/user/createUserRent",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function getAllUserRentByComputerStuffExpirationDateLessThan(data, token) {
    return await fetch("/api/getUserOrders",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}