import {getJson} from "../../../../requests";

export const loadProduct = (product) => (
    {
        type: "LOAD_EXAMPLES",
        payload: product
    }
)

export const getProductById = (productId)=>(dispatch) => {
    dispatch(loadingExamples())
    return  getJson({
            body: {},
            url: "http://localhost:8081/product/" + productId
        }).then(product => {dispatch(loadProduct(product))}) //setProduct(value)
            .catch(() => dispatch(errorLoad()))
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
