import { useState, useEffect } from "react";
import { useSelector } from "react-redux";

export const useAuthStatus = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false)
    const [isAdmin, setIsAdmin] = useState(false)
    const [isLoading, setIsLoading] = useState(true)

    const {user} = useSelector((state) => state.auth)
    
    useEffect(() => {
        if(user){
            setIsLoggedIn(true)
            user.isAdmin && setIsAdmin(true)
        }else {
            setIsLoggedIn(false)
            setIsAdmin(false)
        }
        setIsLoading(false)
    }, [user])
    return {isAdmin, isLoading, isLoggedIn} 
}

