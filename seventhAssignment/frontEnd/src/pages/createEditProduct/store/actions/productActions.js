import {getJson, postJson, putJson} from "../../../../requests";

const receiveProduct = (product) => (
    {
        type: "RECEIVE_PRODUCT",
        payload: product
    }
)

const getProductById = (productId) => (dispatch) => {
    dispatch(loadingProduct())
    return getJson({
        body: {},
        url: "http://localhost:8081/product/" + productId
    }).then(product => {
        dispatch(receiveProduct(product))
    })
        .catch(() => dispatch(errorLoad()))
}

const postProduct = (product) => (dispatch) => {
    return postJson({
        body: product,
        url: "http://localhost:8081/product",
        redirect: "manual"
    }).then(() => {
        window.location.href = (`/productList`)
    })
        .catch(() => dispatch(errorLoad()))
}

const putProduct = (product) => (dispatch) => {
    return putJson({
        body: product,
        url: "http://localhost:8081/product"
    }).then(window.location.href = (`/productList`))
        .catch(() => dispatch(errorLoad()))
}

const setProductIsCreating = ()=>(dispatch) => {
    dispatch(creatingProduct())
}

const loadingProduct = () => (
    {
        type: "LOADING_PRODUCT"
    }
)

const errorLoad = () => (
    {
        type: "ERROR_LOAD"
    }
)

const creatingProduct = () => (
    {
        type: "CREATING_PRODUCT"
    }
)

export default {getProductById, postProduct, putProduct,setProductIsCreating}
