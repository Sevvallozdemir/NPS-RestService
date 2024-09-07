let user = null;
let selectedRating = null;

function login() {
    const phoneNumber = document.getElementById("phone-number").value;
    const password = document.getElementById("password").value;

    // API Call to login
    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ phoneNumber, password })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                user = data.user;
                document.getElementById("login-section").style.display = "none";
                document.getElementById("survey-section").style.display = "block";
                loadSurvey();
            } else {
                alert('Login failed!');
            }
        });
}

function loadSurvey() {
    fetch(`/api/survey?userId=${user.id}`)
        .then(response => response.json())
        .then(survey => {
            document.getElementById("survey-question").textContent = survey.question;
            const ratingOptionsDiv = document.getElementById("rating-options");
            ratingOptionsDiv.innerHTML = '';
            survey.options.forEach(option => {
                const span = document.createElement("span");
                span.textContent = option;
                span.className = "rating";
                span.onclick = () => selectRating(option);
                ratingOptionsDiv.appendChild(span);
            });
        });
}

function selectRating(rating) {
    selectedRating = rating;
    document.querySelectorAll(".rating").forEach(el => {
        el.style.color = el.textContent == rating ? "#007bff" : "#000";
    });
}

function submitSurvey() {
    if (selectedRating === null) {
        alert("Please select a rating!");
        return;
    }

    fetch('/api/survey/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ userId: user.id, rating: selectedRating })
    })
        .then(response => {
            if (response.ok) {
                document.getElementById("survey-section").style.display = "none";
                document.getElementById("result-section").style.display = "block";
            } else {
                alert("Submission failed!");
            }
        });
}
