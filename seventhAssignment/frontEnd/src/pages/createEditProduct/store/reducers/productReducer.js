const initialState = {
    isLoading: false,
    isError: false,
    product: {
        id: null,
        name: null,
        categoryId: null
    }
};

const productReducer = (state = initialState, {type, payload}) => {
    switch (type) {
        case "RECEIVE_PRODUCT":
            return {
                ...state,
                product: {...payload},
                isLoading: false
            }
        case "LOADING_PRODUCT":
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
export default productReducer