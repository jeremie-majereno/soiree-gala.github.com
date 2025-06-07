
document.addEventListener('DOMContentLoaded', function() {
    // Fonction pour gérer les messages
    function handleMessages() {
        const alerts = document.querySelectorAll('.alert');

        alerts.forEach(alert => {
            // Fermer les messages après 5 secondes
            setTimeout(() => {
                if (alert && alert.parentNode) {
                    const bsAlert = new bootstrap.Alert(alert);
                    bsAlert.close();
                }
            }, 5000);

            // Ajouter un bouton de fermeture
            const closeBtn = document.createElement('button');
            closeBtn.type = 'button';
            closeBtn.className = 'btn-close';
            closeBtn.setAttribute('data-bs-dismiss', 'alert');
            closeBtn.setAttribute('aria-label', 'Close');
            alert.appendChild(closeBtn);
        });
    }

    // Fonction pour basculer la visibilité du mot de passe
    function setupPasswordToggle() {
        const toggleButtons = document.querySelectorAll('.toggle-password');

        toggleButtons.forEach(button => {
            button.addEventListener('click', function() {
                const targetId = this.getAttribute('data-target');
                const passwordInput = document.getElementById(targetId);

                if (passwordInput) {
                    const icon = this.querySelector('i');

                    if (passwordInput.type === 'password') {
                        passwordInput.type = 'text';
                        icon.classList.remove('fa-eye');
                        icon.classList.add('fa-eye-slash');
                    } else {
                        passwordInput.type = 'password';
                        icon.classList.remove('fa-eye-slash');
                        icon.classList.add('fa-eye');
                    }
                }
            });
        });
    }

    // Fonction pour la validation des formulaires
    function setupFormValidation() {
        // Activer la validation Bootstrap pour tous les formulaires
        const forms = document.querySelectorAll('.needs-validation');

        forms.forEach(form => {
            form.addEventListener('submit', function(event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }

                form.classList.add('was-validated');
            }, false);
        });
    }

    // Animation d'entrée pour les éléments
    function setupAnimations() {
        const animatedElements = document.querySelectorAll('.fade-in');
        animatedElements.forEach(el => {
            el.style.opacity = '0';
            setTimeout(() => {
                el.style.transition = 'opacity 0.5s ease';
                el.style.opacity = '1';
            }, 100);
        });
    }

    // Initialisation
    handleMessages();
    setupPasswordToggle();
    setupFormValidation();
    setupAnimations();

    // Ajouter un effet de pulsation aux boutons importants
    const pulseButtons = document.querySelectorAll('.pulse');
    pulseButtons.forEach(btn => {
        btn.addEventListener('mouseenter', function() {
            this.classList.add('pulse');
        });

        btn.addEventListener('mouseleave', function() {
            this.classList.remove('pulse');
        });
    });
});