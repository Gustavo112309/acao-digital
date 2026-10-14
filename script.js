const togglePassword = document.querySelector('#togglePassword');
const passwordInput = document.querySelector('#senha');

if (togglePassword && passwordInput) {
    togglePassword.addEventListener('click', function () {
        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordInput.setAttribute('type', type);
        this.classList.toggle('fa-eye');
        this.classList.toggle('fa-eye-slash');
    });
}

const loginForm = document.querySelector('#loginForm');
if (loginForm) {
    loginForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const email = document.querySelector('#email').value;
        const senha = document.querySelector('#senha').value;

        if (email && senha) {
            alert('Login efetuado com sucesso!');
        }
    });
}