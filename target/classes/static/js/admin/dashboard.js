document.addEventListener("DOMContentLoaded", () => {
    // Event listener for buttons
    const buttons = document.querySelectorAll(".btn");
    buttons.forEach((button) => {
        button.addEventListener("click", (e) => {
            const action = button.textContent.trim();
            alert(`You clicked the "${action}" button`);
        });
    });

});
