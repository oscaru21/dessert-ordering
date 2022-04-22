import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getMenuItem, reset } from "../features/menuItems/menuItemSlice";
import {
  getItemReviews,
  createItemReview,
  reset as reviewsReset,
} from "../features/itemReviews/itemReviewSlice";
import { FaPlus } from "react-icons/fa";
import BackButton from "../components/BackButton";
import Spinner from "../components/Spinner";
import { useParams } from "react-router-dom";
import Modal from "react-modal";
import ReviewItem from "../components/ReviewItem";
import { toast } from "react-toastify";

const customStyles = {
  content: {
    width: "80%",
    top: "50%",
    left: "50%",
    right: "auto",
    bottom: "auto",
    marginRight: "-50%",
    transform: "translate(-50%, -50%)",
    position: "relative",
  },
};

function MenuItem() {
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [reviewText, setReviewText] = useState("");

  const { menuItem, isLoading, isSuccess, isError, message } = useSelector(
    (state) => state.menuItems
  );

  const { itemReviews } = useSelector((state) => state.itemReviews);

  const { user } = useSelector((state) => state.auth);

  const dispatch = useDispatch();
  const { menuItemId } = useParams();

  const { imgUrl, name, description, price } = menuItem || {};

  useEffect(() => {
    if (isError) {
      toast.error(message);
    }

    dispatch(getMenuItem(menuItemId));
    dispatch(getItemReviews(menuItemId));
    //if component is unmounted
    return () => {
      if (isSuccess) {
        dispatch(reset);
      }
    };
  }, [isError, isSuccess, message, menuItemId, modalIsOpen]);

  const openModal = () => setModalIsOpen(true);
  const closeModal = () => setModalIsOpen(false);

  const onSubmitReview = (e) => {
    e.preventDefault();

    dispatch(createItemReview({menuItemId, text: reviewText}));
    closeModal();
  };

  if (isLoading) {
    return <Spinner />;
  }

  return (
    <div>
      <div className="menu-item-page">
        <div className="menu-item-header">
          <BackButton url="/" />
        </div>
        <div className="menu-item-product">
          <div className="grid">
            <div className="menu-item-image">
              <img src={imgUrl} alt="" />
            </div>
            <div className="menu-item-details">
              <div className="menu-item-text">
                <div className="menu-item-name">{name}</div>
                <div className="menu-item-description">
                  <p>{description}</p>
                </div>
              </div>

              <div className="menu-item-price">
                <p>${price}</p>
                <div className="btn btn-secondary">Add to Cart</div>
              </div>
            </div>
          </div>
        </div>

        <div className="reviews">
          {user && <button onClick={openModal} className="btn">
            <FaPlus /> Add Review
          </button>}
          
          <h2>Reviews</h2>
        </div>

        <Modal
          isOpen={modalIsOpen}
          style={customStyles}
          contentLabel="Add Note"
          onRequestClose={closeModal}
          ariaHideApp={false}
        >
          <h2>Add Review</h2>
          <button className="btn-close" onClick={closeModal}>
            X
          </button>
          <form onSubmit={onSubmitReview}>
            <div className="form-group">
              <textarea
                name="reviewText"
                id="reviewText"
                value={reviewText}
                placeholder="Review"
                onChange={(e) => setReviewText(e.target.value)}
              ></textarea>
            </div>
            <div className="form-group">
              <button className="btn">Submit</button>
            </div>
          </form>
        </Modal>

        {itemReviews.map((review) => (
          <ReviewItem key={review.id} review={review} />
        ))}
      </div>
    </div>
  );
}

export default MenuItem;
