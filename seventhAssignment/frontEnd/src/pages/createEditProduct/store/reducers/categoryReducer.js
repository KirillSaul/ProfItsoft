const initialState = {
    isLoading: false,
    isError: false,
    categories: []
};

const categoryReducer = (state = initialState, {type, payload}) => {
    switch (type) {
        case "RECEIVE_CATEGORIES":
            return {
                ...state,
                categories: [...payload],
                isLoading: false
            }
        case "LOADING_CATEGORIES":
            return {
                ...state,
                isLoading: true
            }
        case "ERROR_LOAD":
            return {...state, isLoading: false, isError: true}
        default:
            return state;
    }
}
export default categoryReducer