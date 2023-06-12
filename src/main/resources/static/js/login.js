function enter() {
    if (window.event.keyCode === 13) {
        login();
    }
}

function login() {
    const id = existId('id');
    const password = existId('password');

    const data = {};
    data.mem_id = id.value;
    data.mem_pass = password.value;
    console.log(data);
    httpUtil.defaultRequest('/member/login', 'post', data,
        function (data) {
            if(data.data.success) {
                location.href = '/'
            } else {
                id.classList.add('is-invalid');
                existId('invalidEmail').innerText = '';
                password.classList.add('is-invalid');
                existId('invalidPassword').innerText = data.data.message;
            }
        });
}

