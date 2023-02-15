import {postJson} from "../../../requests";
import {useState} from "react";
import Button from 'components/Button';
import Box from 'components/Box';
import Grid from 'components/Grid';
import Link from 'components/Link'
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import productActions from "../../productList/store/actions/actions";

const ProductList = ({authorities}) => {
    const [productList, setProductList] = useState([]);

    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(productActions.getProducts())
    }, [])

    const products = useSelector((state) => {
        console.log("lol"+state.reducer.products);
        return state.reducer.products
    })

    useEffect(() => {
        console.log(products);
        setProductList((prevState) => ([...prevState, products]));
    }, [products])


    return (
        <div>
            <Button variant="contained" color="primary" href="/createEditProduct">
                Create
            </Button>
            <div> {
                productList.map(value =>
                    (
                        <Grid>
                            <Box sx={{m: 2}}/>
                            {value.name}
                            <Link to={"/createEditProduct/" + value.id }>
                                <Button variant="contained" color="primary">
                                    Edit
                                </Button>
                            </Link>
                            <Button variant="contained" color="secondary" onClick={()=>productActions.deleteProductById(value.id)}>
                                Delete
                            </Button>

                        </Grid>

                    )
                )
            } </div>
        </div>
    )

}
export default ProductList