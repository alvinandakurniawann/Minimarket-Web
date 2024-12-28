document.addEventListener('DOMContentLoaded', function() {
    // Get the current page
    const currentPage = window.location.pathname.includes('login.html') ? 'login' : 'register';
    
    // Get the form
    const form = document.getElementById(currentPage + 'Form');
    
    form.addEventListener('submit', async function(e) {
        e.preventDefault();
        
        // Get form data
        const formData = new FormData(form);
        const data = Object.fromEntries(formData.entries());
        
        // Validation
        if (currentPage === 'register') {
            if (data.password !== data.confirmPassword) {
                showError('Passwords do not match');
                return;
            }
            
            if (data.password.length < 6) {
                showError('Password must be at least 6 characters long');
                return;
            }
        }
        
        try {
            // Simulate API call
            const response = await simulateAuth(data, currentPage);
            
            if (response.success) {
                // Store token in localStorage
                localStorage.setItem('authToken', response.token);
                
                // Redirect to dashboard or home page
                window.location.href = '/dashboard.html';
            } else {
                showError(response.message);
            }
        } catch (error) {
            showError('An error occurred. Please try again.');
        }
    });
});

function showError(message) {
    // Remove existing error messages
    const existingError = document.querySelector('.error-message');
    if (existingError) {
        existingError.remove();
    }
    
    // Create and show new error message
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
    errorDiv.textContent = message;
    
    const submitButton = document.querySelector('.btn-submit');
    submitButton.parentNode.insertBefore(errorDiv, submitButton);
}

// Simulate authentication API call
async function simulateAuth(data, type) {
    // This is a mock function - replace with actual API calls
    return new Promise((resolve) => {
        setTimeout(() => {
            if (type === 'login') {
                resolve({
                    success: true,
                    token: 'mock-jwt-token',
                    message: 'Login successful'
                });
            } else {
                resolve({
                    success: true,
                    token: 'mock-jwt-token',
                    message: 'Registration successful'
                });
            }
        }, 1000);
    });
}