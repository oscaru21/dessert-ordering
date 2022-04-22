
function ReviewItem({review}) {
  const {text} = review

  return (
    <div
      className='review'
      style={{
        backgroundColor: '#fff',
        color: '#000',
      }}
    >
      <div className="review-head">
        <p>@{review.user}</p>
      </div>
      <div className='review-date'>
        {review.creationDate}
      </div>
      <p>{text}</p>
    </div>
  )
}

export default ReviewItem