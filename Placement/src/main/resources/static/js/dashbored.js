// Sidebar toggle function
function toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    sidebar.classList.toggle('collapsed');
}

// Chart.js Example - Line Chart
const ctx = document.getElementById('placementChart').getContext('2d');
const placementChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
        datasets: [{
            label: 'Bookings',
            data: [10, 20, 15, 25, 30, 45],
            borderColor: '#3e95cd',
            backgroundColor: 'rgba(62, 149, 205, 0.2)',
            fill: true,
            tension: 0.4
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                display: true
            }
        }
    }
});

// Another Chart.js example for application sources
const sourceCtx = document.getElementById('sourceChart').getContext('2d');
const sourceChart = new Chart(sourceCtx, {
    type: 'pie',
    data: {
        labels: ['Direct', 'Referral', 'Social Media'],
        datasets: [{
            data: [300, 50, 100],
            backgroundColor: ['#007bff', '#28a745', '#fd7e14'],
        }]
    },
    options: {
        responsive: true
    }
});
