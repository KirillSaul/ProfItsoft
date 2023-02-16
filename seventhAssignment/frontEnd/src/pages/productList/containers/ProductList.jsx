import {useEffect, useState} from "react";
import Button from 'components/Button';
import Box from 'components/Box';
import Grid from 'components/Grid';
import Link from 'components/Link'
import {useDispatch, useSelector} from "react-redux";
import productActions from "../../productList/store/actions/actions";
import useAccessValidate from "../../../hooks/useAccessValidate";

const ProductList = ({authorities}) => {
    const [newProducts, setNewProducts] = useState([]);

    const canSeeList = useAccessValidate({
        ownedAuthorities: authorities,
        neededAuthorities: ['МОЖНО_ВОТ_ЭТУ_ШТУКУ'],
    });

    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(productActions.getProducts())
    }, [])

    const products = useSelector((state) => {
        return state.reducer.products
    })

    useEffect(() => {
        setNewProducts(()=>[...products]);
    }, [products])

    useEffect(() => {

        console.log(products)
        console.log("new: ")
        console.log(newProducts)
    }, [newProducts])

    return (
        <div>
            {canSeeList &&
                <div>
                    <Button variant="contained" color="primary" href="/createEditProduct">
                        Create
                    </Button>
                    <div> {
                        newProducts.map(product =>
                            (
                                <Grid>
                                    <Box sx={{m: 2}}/>
                                    {product.name}
                                    <Link to={"/createEditProduct/" + product.id}>
                                        <Button variant="contained" color="primary">
                                            Edit
                                        </Button>
                                    </Link>
                                    <Button variant="contained" color="secondary"
                                            onClick={() => dispatch(productActions.deleteProductById(product.id))}>
                                        Delete
                                    </Button>
                                </Grid>
                            )
                        )
                    }
                    </div>
                </div>
            }
        </div>
    )

}
export default ProductList