const initialState = {
    examples: []
}

function loadExamples() {
   return fetch("http://localhost:8080/math/examples?count=5", {
        method: "get",
        headers: {'Content-Type': 'application/json'}
    }).then(value => value.json())

}

function reducer(state = initialState, action) {
    switch (action.type) {
        case "LOAD_EXAMPLES":
            return  {examples:loadExamples()}
        default:
            return state;
    }
}

export default reducer;