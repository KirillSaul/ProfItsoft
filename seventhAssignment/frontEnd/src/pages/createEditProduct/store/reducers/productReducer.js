const initialState = {
    isLoading: false,
    isError: false,
    isCreating: false,
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
                isCreating: false,
                isLoading: false
            }
        case "LOADING_PRODUCT":
            return {
                ...state,
                isCreating: false,
                isLoading: true
            }
        case "ERROR_LOAD":
            return {...state, isLoading: false, isCreating: false, isError: true}
        case "CREATING_PRODUCT":
            return {...state, isCreating: true}
        default:
            return state;
    }
}
export default productReducer