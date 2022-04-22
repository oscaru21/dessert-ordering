import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { getMenuItems, reset } from "../features/menuItems/menuItemSlice";
import Card from "../components/Card";
import Spinner from "../components/Spinner";
import Hero from "../components/Hero";

function Home() {
  const { menuItems, isLoading, isSuccess } = useSelector(
    (state) => state.menuItems
  );
  const { user } = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  useEffect(() => {
    return () => {
      if (isSuccess) {
        dispatch(reset());
      }
    };
  }, [dispatch, isSuccess]);

  useEffect(() => {
    dispatch(getMenuItems());
  }, [dispatch]);

  if (isLoading) {
    return <Spinner />;
  }

  return (
    <>
      <Hero />
      <div className="grid">
        {menuItems.map((menuItem) => (
          <Card key={menuItem.id} cardData={menuItem} />
        ))}
      </div>
    </>
  );
}

export default Home;
