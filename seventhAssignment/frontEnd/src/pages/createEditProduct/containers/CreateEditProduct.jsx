import {getJson, postJson} from "../../../requests";
import {useEffect, useState} from "react";
import {useLocation, useParams} from "react-router-dom";
import Button from 'components/Button';
import Box from 'components/Box';
import Grid from 'components/Grid';
import InputName from "./components/InputName";
import TextField from "components/TextField";
import {useDispatch, useSelector} from "react-redux";
import productActions from "../store/actions/actions";

const CreateEditProduct = ({authorities}) => {
    const initialState = {id: null, name: null, categoryId: null}
    const [newProduct, setNewProduct] = useState(initialState);


    const dispatch = useDispatch();


    const {productId} = useParams()
    useEffect(() => {
        if (productId !== undefined) {
            dispatch(productActions.getProductById(productId))
        }

    }, [])

    const product = useSelector((state) => {
        return state.reducer.product
    })
    useEffect(() => {
        setNewProduct((prevState) => ({...prevState, ...product}));
    }, [product])

    return (
        <div>
            <div>name:</div>
            <div>

                <TextField value={newProduct.name !== null ? newProduct.name : ""} onChange={(e) => {
                    setNewProduct((prevState) => ({...prevState, name: e.target.value}))
                }}></TextField>
            </div>
            <div>categoryId:</div>
            <div>
                <TextField value={newProduct.categoryId !== null ? newProduct.categoryId : ""} onChange={(e) => {
                    setNewProduct((prevState) => ({...prevState, categoryId: e.target.value}))
                }}></TextField>
            </div>
            <Button onClick={() => {
                productId === undefined? dispatch(productActions.postProduct(newProduct)): dispatch(productActions.putProduct(newProduct))
            }}>Save</Button>
        </div>
    )

}
export default CreateEditProduct