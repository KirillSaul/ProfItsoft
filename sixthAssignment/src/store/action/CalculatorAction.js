export const loadExamples = (examples) => (
    {
        type: "LOAD_EXAMPLES",
        examples: examples
    }
)

export const getExamples = (dispatch) => {
    dispatch(loadingExamples())
    setTimeout(() => {
        return fetch("http://localhost:8081/math/examples?count=5", {
            method: "get",
            headers: {'Content-Type': 'application/json'}
        }).then(value => value.json().then((value) => dispatch(loadExamples(value))))
            .catch(() => dispatch(errorLoad()))
    }, 2000)


}

export const loadingExamples = () => (
    {
        type: "LOADING_EXAMPLES"
    }
)

export const errorLoad = () => (
    {
        type: "ERROR_LOAD"
    }
)

