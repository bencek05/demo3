// Fetching all reviews and displaying them
async function fetchReviews() {
  const res = await fetch("http://localhost:3000/reviews");
  const reviews = await res.json();

  const reviewsList = document.getElementById("reviewsList");
  reviewsList.innerHTML = "";

  reviews.forEach(review => {
    const li = document.createElement("li");
    li.innerHTML = `<strong>${review.bookTitle}</strong> by ${review.reviewerName} 
                    <br>Rating: ${review.rating} 
                    <p>${review.reviewText}</p>`;
    reviewsList.appendChild(li);
  });
}

// Posting a new review
document.getElementById("reviewForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const bookTitle = document.getElementById("bookTitle").value;
  const reviewerName = document.getElementById("reviewerName").value;
  const reviewText = document.getElementById("reviewText").value;
  const rating = parseInt(document.getElementById("rating").value);

  const newReview = {
    bookTitle,
    reviewerName,
    reviewText,
    rating,
  };

  // Post the new review to the server
  await fetch("http://localhost:3000/reviews", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(newReview),
  });

  // Clear the form fields
  document.getElementById("reviewForm").reset();

  // Re-fetch and display all reviews
  fetchReviews();
});

// Initial load of reviews
fetchReviews();
