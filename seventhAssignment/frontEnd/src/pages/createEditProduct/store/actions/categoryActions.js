import {getJson} from "../../../../requests";

const receiveCategories = (categories) => (
    {
        type: "RECEIVE_CATEGORIES",
        payload: categories
    }
)

const getCategories = () => (dispatch) => {
    dispatch(loadingCategories())
    return getJson({
        body: {},
        url: "http://localhost:8081/category"
    }).then(categories => {
        dispatch(receiveCategories(categories))
    })
        .catch(() => dispatch(errorLoad()))
}

const loadingCategories = () => (
    {
        type: "LOADING_CATEGORIES"
    }
)

const errorLoad = () => (
    {
        type: "ERROR_LOAD"
    }
)
export default {getCategories}