document.addEventListener("DOMContentLoaded", function() {
	const dropdownBtn = document.getElementById("dropdownBtn");
	const dropdownMenu = document.getElementById("dropdownMenu");

	if (dropdownBtn && dropdownMenu) {
		dropdownBtn.addEventListener("click", function() {
			dropdownMenu.classList.toggle("show");
		});
	}
});


/*-- JavaScript to toggle password visibility --*/


function toggleEyeIcon(input) {
	const icon = input.parentElement.querySelector('.toggle-password');
	if (input.value.length > 0) {
		icon.style.display = 'inline';
	} else {
		icon.style.display = 'none';
	}
}

document.addEventListener('DOMContentLoaded', function() {
	const toggleIcon = document.querySelector('.toggle-password');
	const passwordInput = document.getElementById('password');

	if(toggleIcon){
		toggleIcon.addEventListener('click', function() {
				const type = passwordInput.type === 'password' ? 'text'
					: 'password';
				passwordInput.type = type;

				// Toggle icon
				toggleIcon.classList.toggle('fa-eye');
				toggleIcon.classList.toggle('fa-eye-slash');
			});
	}
	
});





/*Search filter*/

function filterJobs() {
          const input = document.getElementById("jobSearch").value.toLowerCase();
          const jobCards = document.querySelectorAll(".job-card");

          jobCards.forEach((card) => {
              const title = card.querySelector("h3").textContent.toLowerCase();
              card.style.display = title.includes(input) ? "block" : "none";
          });
      }