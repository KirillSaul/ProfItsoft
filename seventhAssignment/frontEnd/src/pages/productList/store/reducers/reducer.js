const initialState = {
    products: [],
    isLoading: false,
    isError: false
};

export default (state = initialState, {type, payload}) => {
    switch (type) {
        case "RECEIVE_PRODUCTS":
            return {
                ...state,
                products: [...payload],
                isLoading: false
            }
        case "LOADING_PRODUCTS":
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
