const initialState = {
    isLoading: false,
    isError: false,
    examples: []
}

function reducer(state = initialState, action) {
    switch (action.type) {
        case "LOAD_EXAMPLES":
            return {
                ...state,
                examples: action.examples,
                isLoading: false
            }
        case "LOADING_EXAMPLES":
            return {...state,
                isLoading: true}
        case "ERROR_LOAD":
            return {...state,isError: true}
        default:
            return state;
    }
}

export default reducer;