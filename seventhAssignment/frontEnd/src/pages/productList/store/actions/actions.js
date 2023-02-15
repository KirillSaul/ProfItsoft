import {deleteJson, postJson} from "../../../../requests";

const receiveProduct = (product) => (
    {
        type: "RECEIVE_PRODUCTS",
        payload: product
    }
)

const getProducts = () => (dispatch) => {
    dispatch(loadingProducts())
    postJson({
        body: {
            page: 0,
            pageSize: 40
        },
        url: "http://localhost:8081/product/_filter"
    }).then(products => {
        dispatch(receiveProduct(products.content))
    })
        .catch(() => dispatch(errorLoad()))
}

const deleteProductById = (productId) => (dispatch) => {
    deleteJson({
        body: {},
        url: "http://localhost:8081/product/" + productId
    }).then(window.location.href = (`/productList`))
        .catch(() => dispatch(errorLoad()))
}

const loadingProducts = () => (
    {
        type: "LOADING_PRODUCTS"
    }
)

const errorLoad = () => (
    {
        type: "ERROR_LOAD"
    }
)
export default {getProducts,deleteProductById}
