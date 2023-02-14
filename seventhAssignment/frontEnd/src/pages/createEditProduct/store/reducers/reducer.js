const initialState = {
    isLoading: false,
    isError: false,
    product: {
        id: null,
        name: null,
        categoryId: null,
    }
};

export default (state = initialState, {type, payload}) => {
    switch (type) {
        case "LOAD_EXAMPLES":
            console.log({...payload})
            return {
                ...state,
                product: payload,
                isLoading: true
            }
        case "LOADING_EXAMPLES":
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
