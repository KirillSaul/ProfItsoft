import {getJson, postJson} from "../../../requests";
import {useEffect, useState} from "react";
import {useLocation} from "react-router-dom";
import Button from 'components/Button';
import Box from 'components/Box';
import Grid from 'components/Grid';
import InputName from "./components/InputName";
import TextField from "components/TextField";
import {useDispatch, useSelector} from "react-redux";
import {getProductById} from "../store/actions/actions";

const CreateEditProduct = ({authorities}) => {
  //  const [product, setProduct] = useState({});
    const productIdUrl = useLocation().pathname.substring(useLocation().pathname.lastIndexOf("/"))
    // if (productIdUrl.length >= 2) {
    //     const getProduct = () => {
    //         getJson({
    //             body: {},
    //             url: "http://localhost:8081/product" + productIdUrl
    //         }).then(value => setProduct(value))
    //     }
    //     getProduct();
    // }

    const dispatch = useDispatch();
    const product = useSelector((reducer)=>   reducer)
    getProductById(2)(dispatch)
    console.log(product)
    // const prod = useSelector(state => state.product)
    // console.log(prod)
    return (
        <div>
            {/*{product.name}*/}
            <div>
                {/*<TextField defaultValue={product.name}></TextField>*/}
                {/*{*/}
                {/*    if productIdUrl.length >= 2 &&*/}
                {/*    product.name*/}

                {/*}*/}
            </div>
        </div>
    )

}
export default CreateEditProduct